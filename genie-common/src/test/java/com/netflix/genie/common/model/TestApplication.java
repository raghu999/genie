/*
 *
 *  Copyright 2014 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.genie.common.model;

import com.netflix.genie.common.exceptions.GenieException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test the Application class.
 *
 * @author tgianos
 */
public class TestApplication {
    private static final String NAME = "pig";
    private static final String USER = "tgianos";
    private static final String VERSION = "1.0";

    private Application a;

    /**
     * Setup the tests.
     */
    @Before
    public void setup() {
        this.a = new Application();
    }

    /**
     * Test the default Constructor.
     */
    @Test
    public void testDefaultConstructor() {
        Assert.assertNull(this.a.getCommands());
        Assert.assertNull(this.a.getConfigs());
        Assert.assertNull(this.a.getEnvPropFile());
        Assert.assertEquals(ApplicationStatus.INACTIVE, this.a.getStatus());
        Assert.assertNull(this.a.getJars());
        Assert.assertNull(this.a.getName());
        Assert.assertNull(this.a.getUser());
        Assert.assertNull(this.a.getVersion());
    }

    /**
     * Test the argument Constructor.
     *
     * @throws GenieException
     */
    @Test
    public void testConstructor() throws GenieException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE, VERSION);
        Assert.assertNull(this.a.getCommands());
        Assert.assertNull(this.a.getConfigs());
        Assert.assertNull(this.a.getEnvPropFile());
        Assert.assertEquals(ApplicationStatus.ACTIVE, this.a.getStatus());
        Assert.assertNull(this.a.getJars());
        Assert.assertEquals(NAME, this.a.getName());
        Assert.assertEquals(USER, this.a.getUser());
        Assert.assertEquals(VERSION, this.a.getVersion());
    }

    /**
     * Test to make sure validation works.
     *
     * @throws GenieException
     */
    @Test
    public void testOnCreateOrUpdateApplication() throws GenieException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE, VERSION);
        this.a.onCreateOrUpdateApplication();
    }

    /**
     * Test to make sure validation works and throws exception when no status entered.
     *
     * @throws GenieException
     */
    @Test(expected = GenieException.class)
    public void testOnCreateOrUpdateApplicationNoStatus() throws GenieException {
        this.a = new Application(NAME, USER, null, VERSION);
        this.a.onCreateOrUpdateApplication();
    }

    /**
     * Make sure validation works on valid apps.
     *
     * @throws GenieException
     */
    @Test
    public void testValidate() throws GenieException {
        this.a = new Application(NAME, USER, ApplicationStatus.ACTIVE, VERSION);
        this.a.validate();
    }

    /**
     * Test setting the status.
     */
    @Test
    public void testSetStatus() {
        Assert.assertEquals(ApplicationStatus.INACTIVE, this.a.getStatus());
        this.a.setStatus(ApplicationStatus.ACTIVE);
        Assert.assertEquals(ApplicationStatus.ACTIVE, this.a.getStatus());
    }

    /**
     * Test setting the property file.
     */
    @Test
    public void testSetEnvPropFile() {
        Assert.assertNull(this.a.getEnvPropFile());
        final String propFile = "s3://netflix.propFile";
        this.a.setEnvPropFile(propFile);
        Assert.assertEquals(propFile, this.a.getEnvPropFile());
    }

    /**
     * Test setting the configs.
     */
    @Test
    public void testSetConfigs() {
        Assert.assertNull(this.a.getConfigs());
        final Set<String> configs = new HashSet<String>();
        configs.add("s3://netflix.configFile");
        this.a.setConfigs(configs);
        Assert.assertEquals(configs, this.a.getConfigs());
    }

    /**
     * Test setting the jars.
     */
    @Test
    public void testSetJars() {
        Assert.assertNull(this.a.getJars());
        final Set<String> jars = new HashSet<String>();
        jars.add("s3://netflix/jars/myJar.jar");
        this.a.setJars(jars);
        Assert.assertEquals(jars, this.a.getJars());
    }

    /**
     * Test setting the tags.
     */
    @Test
    public void testSetTags() {
        Assert.assertNotNull(this.a.getTags());
        final Set<String> tags = new HashSet<String>();
        tags.add("tag1");
        tags.add("tag2");
        this.a.setTags(tags);
        Assert.assertEquals(tags, this.a.getTags());
    }

    /**
     * Test setting the commands.
     */
    @Test
    public void testSetCommands() {
        Assert.assertNull(this.a.getCommands());
        final Set<Command> commands = new HashSet<Command>();
        commands.add(new Command());
        this.a.setCommands(commands);
        Assert.assertEquals(commands, this.a.getCommands());
    }
}