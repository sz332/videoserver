package com.acme.videoserver.storage.svn;

import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.SVNClientAdapterFactory;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.svnkit.SvnKitClientAdapterFactory;

import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;

public class SVNStorage implements Storage {

    private final String url;
    private final String user;
    private final String password;

    public SVNStorage(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public StorageConnection connect() throws StorageAccessException {

        try {
            SvnKitClientAdapterFactory.setup();

            String bestClientType = SVNClientAdapterFactory.getPreferredSVNClientType();
            ISVNClientAdapter svnClient = SVNClientAdapterFactory.createSVNClient(bestClientType);

            svnClient.setUsername(user);
            svnClient.setPassword(password);

            return new SVNStorageConnection(svnClient, url);

        } catch (SVNClientException e) {
            throw new StorageAccessException(e);
        }
    }

}
