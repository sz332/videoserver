package com.acme.videoserver.storage.svn;

import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;

public class SVNStorageConnection implements StorageConnection {

    private final ISVNClientAdapter svnClient;
    private final String url;
    
    public SVNStorageConnection(ISVNClientAdapter svnClient, String url) {
        this.svnClient = svnClient;
        this.url = url;
    }

    @Override
    public RemoteLocation root() throws StorageAccessException {
            return new SVNRemoteLocation(svnClient, url);
    }

    @Override
    public RemoteLocation resolve(String path) throws StorageAccessException {
        return null;
    }

}
