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

package ds.tree;

/**
 * The visitor interface that is used by {@link RadixTreeImpl} for perfroming
 * task on a searched node.
 * 
 * @author Tahseen Ur Rehman 
 * email: tahseen.ur.rehman {at.spam.me.not} gmail.com 
 * @param <T>
 */
public interface Visitor<T> {
    /**
     * This method gets called by @link RadixTreeImpl#visit(String, Visitor)} 
     * when it finds a node matching key given to it.
     * 
     * @param key The that got matched
     * @param parent The parent of he node
     * @param node The matching node
     */
    public void visit(String key, RadixTreeNode<T> parent, RadixTreeNode<T> node);

    /**
     * return any result calcuklated by the visitor.
     * 
     * @return
     */
    public Object getResult();
}
