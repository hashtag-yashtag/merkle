package merkle.implementation;

import merkle.Configuration;
import merkle.ICollisionGenerator;
import merkle.IMerkleTree;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * TASK 4 BONUS
 * THIS IS FOR BONUS POINTS
 * DO NOT DO THIS BEFORE COMPLETING EVERYTHING ELSE FIRST
 *
 * @author Yash Gupta
 * @pso 17
 * @date 10/29/2016
 */

public class CollisionGenerator extends ICollisionGenerator {

    /**
     * Given a <i>merkleTree</i> this function needs to
     * generate a file which will generate the merkleTree
     * The file then has to be dumped to <i>outputFile</i>
     * Basic code for writing blocks to a file is provided.
     */
    @Override
    public void generateCollision(File outputFile, IMerkleTree merkleTree) throws Exception {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            byte[] bytes = new byte[Configuration.blockSize];
            int length = merkleTree.getTree().length;
            for (int i = length/2; i < length; i++) {
                bytes = dehexify(merkleTree.getNode(i).getHash());
                bufferedOutputStream.write(bytes);
            }

            bufferedOutputStream.flush();
        }
    }

    public byte[] dehexify(String hexString) {
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        for (int i=0; i<len; i++) {
            int index = i*2;
            bytes[i] = (byte)Integer.parseInt(hexString.substring(index, index+2), 16);
        }
        return bytes;
    }
}