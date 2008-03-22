/*
The MIT License

Copyright (c) 2008 Tahseen Ur Rehman

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package ds.tree.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ds.tree.DuplicateKeyException;
import ds.tree.RadixTreeImpl;

/**
 * Unit test for {@link RadixTreeImpl}
 * 
 * @author Tahseen Ur Rehman 
 * email: tahseen.ur.rehman {at.spam.me.not} gmail.com 
 */
public class RadixTreeImplTest {
    @Test
    public void testInsert() {
        RadixTreeImpl<String> trie = new RadixTreeImpl<String>();

        try {
            trie.insert("apple", "apple");
            trie.insert("bat", "bat");
            trie.insert("ape", "ape");
            trie.insert("bath", "bath");
            trie.insert("banana", "banana");
        } catch (DuplicateKeyException e) {
            Assert.fail("Found a duplicate when no duplicated expected");
        }
        
        boolean result = false;
        try {
            trie.insert("apple", "apple2");
        } catch (DuplicateKeyException e) {
            result = true;
        }
        
        Assert.assertTrue(result);

        Assert.assertEquals(trie.find("apple"), "apple");
        Assert.assertEquals(trie.find("bat"), "bat");
        Assert.assertEquals(trie.find("ape"), "ape");
        Assert.assertEquals(trie.find("bath"), "bath");
        Assert.assertEquals(trie.find("banana"), "banana");
    }
    
    @Test
	public void testInsert2() {
		RadixTreeImpl<String> trie = new RadixTreeImpl<String>();

		try {
			trie.insert("xbox 360", "xbox 360");
			trie.insert("xbox", "xbox");
			trie.insert("xbox 360 games", "xbox 360 games");
			trie.insert("xbox games", "xbox games");
		} catch (DuplicateKeyException e) {
			Assert.fail("Found a duplicate when no duplicated expected");
		}
		
		try {
			trie.insert("xbox xbox 360", "xbox xbox 360");
			trie.insert("xbox xbox", "xbox xbox");
			trie.insert("xbox 360 xbox games", "xbox 360 xbox games");
			trie.insert("xbox games 360", "xbox games 360");
			trie.insert("xbox 360 360", "xbox 360 360");
			trie.insert("xbox 360 xbox 360", "xbox 360 xbox 360");
			trie.insert("360 xbox games 360", "360 xbox games 360");
			trie.insert("xbox xbox 361", "xbox xbox 361");
		} catch (DuplicateKeyException e) {
			Assert.fail("Found a duplicate when no duplicated expected");
		}
	}

    @Test
    public void testDelete() {
        RadixTreeImpl<String> trie = new RadixTreeImpl<String>();

        try {
            trie.insert("apple", "apple");
            trie.insert("appleshack", "appleshack");
            trie.insert("appleshackcream", "appleshackcream");
            trie.insert("applepie", "applepie");
            trie.insert("ape", "ape");
        } catch (DuplicateKeyException e) {
            Assert.fail("Found a duplicate when no duplicated expected");
        }

        Assert.assertTrue(trie.contains("apple"));
        Assert.assertTrue(trie.delete("apple"));
        Assert.assertFalse(trie.contains("apple"));

        Assert.assertTrue(trie.contains("applepie"));
        Assert.assertTrue(trie.delete("applepie"));
        Assert.assertFalse(trie.contains("applepie"));

        Assert.assertTrue(trie.contains("appleshack"));
        Assert.assertTrue(trie.delete("appleshack"));
        Assert.assertFalse(trie.contains("appleshack"));

        // try to delete "apple" again this should fail
        Assert.assertFalse(trie.delete("apple"));

        // try to find "ape" and "appleshackcream"
        Assert.assertTrue(trie.contains("appleshackcream"));
        Assert.assertTrue(trie.contains("ape"));

        // try to delete "ap" this should fail.
        Assert.assertFalse(trie.delete("ap"));
    }

    @Test
    public void testFind() {
        RadixTreeImpl<String> trie = new RadixTreeImpl<String>();

        try {
            trie.insert("apple", "apple");
            trie.insert("appleshack", "appleshack");
            trie.insert("appleshackcream", "appleshackcream");
            trie.insert("applepie", "applepie");
            trie.insert("ape", "ape");
        } catch (DuplicateKeyException e) {
            Assert.fail("Found a duplicate when no duplicated expected");
        }

        // we shou7ld be able to find all of these
        Assert.assertNotNull(trie.find("apple"));
        Assert.assertNotNull(trie.find("appleshack"));
        Assert.assertNotNull(trie.find("appleshackcream"));
        Assert.assertNotNull(trie.find("applepie"));
        Assert.assertNotNull(trie.find("ape"));

        // try to delete "apple" again this should fail
        Assert.assertNull(trie.find("ap"));
        Assert.assertNull(trie.find("apple2"));
        Assert.assertNull(trie.find("appl"));
        Assert.assertNull(trie.find("app"));
        Assert.assertNull(trie.find("appples"));
    }

    @Test
    public void testContains() {
        RadixTreeImpl<String> trie = new RadixTreeImpl<String>();

        try {
            trie.insert("apple", "apple");
            trie.insert("appleshack", "appleshack");
            trie.insert("appleshackcream", "appleshackcream");
            trie.insert("applepie", "applepie");
            trie.insert("ape", "ape");
        } catch (DuplicateKeyException e) {
            Assert.fail("Found a duplicate when no duplicated expected");
        }

        // we shou7ld be able to find all of these
        Assert.assertTrue(trie.contains("apple"));
        Assert.assertTrue(trie.contains("appleshack"));
        Assert.assertTrue(trie.contains("appleshackcream"));
        Assert.assertTrue(trie.contains("applepie"));
        Assert.assertTrue(trie.contains("ape"));

        // try to delete "apple" again this should fail
        Assert.assertFalse(trie.contains("ap"));
        Assert.assertFalse(trie.contains("apple2"));
        Assert.assertFalse(trie.contains("appl"));
        Assert.assertFalse(trie.contains("app"));
        Assert.assertFalse(trie.contains("appples"));
    }

    @Test
    public void testSearchPrefix() {
        RadixTreeImpl<String> trie = new RadixTreeImpl<String>();

        try {
            trie.insert("apple", "apple");
            trie.insert("appleshack", "appleshack");
            trie.insert("appleshackcream", "appleshackcream");
            trie.insert("applepie", "applepie");
            trie.insert("ape", "ape");
        } catch (DuplicateKeyException e) {
            Assert.fail("Found a duplicate when no duplicated expected");
        }

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("appleshack");
        expected.add("applepie");
        expected.add("apple");

        ArrayList<String> result = trie.searchPrefix("appl", 3);
        Assert.assertTrue(expected.size() == result.size());
        Set<String> resultSet = new HashSet<String>(result);
        for (String key : expected) {
            Assert.assertTrue(resultSet.contains(key));
        }

        expected.add("appleshackcream");

        result = trie.searchPrefix("app", 10);
        Assert.assertTrue(expected.size() == result.size());
        resultSet = new HashSet<String>(result);
        for (String key : expected) {
            Assert.assertTrue(resultSet.contains(key));
        }
    }
    
    @Test
    public void testGetSize() {
        RadixTreeImpl<String> trie = new RadixTreeImpl<String>();

        try {
            trie.insert("apple", "apple");
            trie.insert("appleshack", "appleshack");
            trie.insert("appleshackcream", "appleshackcream");
            trie.insert("applepie", "applepie");
            trie.insert("ape", "ape");
        } catch (DuplicateKeyException e) {
            Assert.fail("Found a duplicate when no duplicated expected");
        }
        
        Assert.assertTrue(trie.getSize() == 5);
        
        trie.delete("appleshack");
        
        Assert.assertTrue(trie.getSize() == 4);
    }
}
