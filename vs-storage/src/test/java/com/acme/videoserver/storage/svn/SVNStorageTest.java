package com.acme.videoserver.storage.svn;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.acme.videoserver.core.storage.RemoteLocation;
import com.acme.videoserver.core.storage.Storage;
import com.acme.videoserver.core.storage.StorageAccessException;
import com.acme.videoserver.core.storage.StorageConnection;

/**
 * Create a docker container the following way
 * 
 * docker volume create svn-root docker run -dit --name svn-server -v
 * svn-root:/home/svn -p 7443:80 -p 3960:3960 -w /home/svn elleflorio/svn-server
 * docker exec -t svn-server htpasswd -b /etc/subversion/passwd user 12345
 * docker exec -it svn-server svnadmin create videoserver
 * 
 * docker exec -it svn-server /bin/sh cd videoserver/conf
 * 
 * edit passwd
 * 
 * vi passwd add a new line to users: user=12345
 * 
 * edit svnserve.conf
 * 
 * vi remove the comments
 * 
 * auth-access=write password-db=passwd
 *
 * Source:
 * 
 * https://gist.github.com/dpmex4527/1d702357697162384d31d033a7d505eb
 * https://medium.com/@elle.florio/the-svn-dockerization-84032e11d88d
 *
 * 
 * Because I was testing on windows, localhost is not resolved, and the ip
 * provided by docker-machine has to be used
 * 
 * docker-machine ip
 * 
 * 
 * @author Zsolt
 */
public class SVNStorageTest {

    private static final String URL = "http://192.168.99.100:7443/svn/videoserver";
    private static final String USER = "user";
    private static final String PASSWORD = "12345";

    @Test
    public void _001_testConnect() throws StorageAccessException {

        Storage storage = new SVNStorage(URL);

        StorageConnection connect = storage.connect(USER, PASSWORD);

        Assert.assertNotNull(connect);
    }

    @Test
    public void _002_testList() throws StorageAccessException {

        Storage storage = new SVNStorage(URL);

        StorageConnection connect = storage.connect(USER, PASSWORD);
        Assert.assertNotNull(connect);

        RemoteLocation remoteLocation = connect.root();
        Assert.assertNotNull(remoteLocation);

        List<RemoteLocation> children = remoteLocation.children();
        Assert.assertNotNull(children);
    }

}
