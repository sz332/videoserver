package com.acme.videoserver.storage.svn;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.ISVNInfo;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.SVNRevision;
import org.tigris.subversion.svnclientadapter.SVNUrl;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.StorageAccessException;

public class SVNRemoteLocation implements RemoteLocation {

    private final ISVNClientAdapter svnClient;
    private final String url;

    public SVNRemoteLocation(ISVNClientAdapter svnClient, String url) {
        this.svnClient = svnClient;
        this.url = url;
    }

    @Override
    public String name() throws StorageAccessException {

        try {
            ISVNInfo info = svnClient.getInfo(new SVNUrl(url));

            return info.getFile()
                    .getName();

        } catch (MalformedURLException | SVNClientException e) {
            throw new StorageAccessException(e);
        }

    }

    @Override
    public String path() throws StorageAccessException {
        return url;
    }

    @Override
    public byte[] download() throws StorageAccessException {
        try {
            InputStream is = svnClient.getContent(new SVNUrl(url), SVNRevision.HEAD);

            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            return bytes;
        } catch (SVNClientException | IOException e) {
            throw new StorageAccessException(e);
        }
    }

    @Override
    public boolean hasChildren() throws StorageAccessException {

        try {
            ISVNDirEntry[] list = svnClient.getList(new SVNUrl(url), SVNRevision.HEAD, false);
            return list.length > 0;
        } catch (MalformedURLException | SVNClientException e) {
            throw new StorageAccessException(e);
        }

    }

    @Override
    public List<RemoteLocation> children() throws StorageAccessException {

        try {
            ISVNDirEntry[] list = svnClient.getList(new SVNUrl(url), SVNRevision.HEAD, false);

            return Arrays.asList(list)
                    .stream()
                    .map(entry -> new SVNRemoteLocation(svnClient, entry.getPath()))
                    .collect(Collectors.toList());

        } catch (MalformedURLException | SVNClientException e) {
            throw new StorageAccessException(e);
        }
    }

}
