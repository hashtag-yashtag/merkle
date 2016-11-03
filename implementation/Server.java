package merkle.implementation;

import merkle.IMerkleTree;
import merkle.IServer;

import java.util.LinkedList;
import java.util.List;


/**
 * TASK 2
 *
 * @author Yash Gupta
 * @pso 17
 * @date 10/29/2016
 */
public class Server extends IServer {

    /**
     * Given a node to verify identified by <i>blockToTest</i>
     * which corresponds to the node received by calling <i>merkleTree.getNode(blockToTest)</i>
     * this function generates the path siblings which are required for verification
     * The returned list should contains Nodes in order from node to the root, i.e. bottom up
     */
    public List<IMerkleTree.Node> generateResponse(int blockToTest) {
        List<IMerkleTree.Node> verificationList = new LinkedList<>();
        verificationList.add(merkleTree.getNode(blockToTest));

        int currentBlock = blockToTest;

        //Run a loop till we reach the root at index 1
        while (currentBlock != 1) {

            //if the index of the current block is even, we add the next element
            //and if the index is odd, we add the previous element to the list.
            if (currentBlock % 2 == 0) {
                verificationList.add(merkleTree.getNode(currentBlock + 1));
            }
            else {
                verificationList.add(merkleTree.getNode(currentBlock - 1));
            }

            //divide the currentBlock by two to reach the parent block's level.
            currentBlock = currentBlock / 2;
        }

        return verificationList;

    }
}
