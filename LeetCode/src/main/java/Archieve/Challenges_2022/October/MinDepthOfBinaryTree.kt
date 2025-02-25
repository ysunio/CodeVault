package Archieve.Challenges_2022.October

fun main() {

}

class TreeNode(val value: Int){
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun minDepth(root: TreeNode?): Int {
    if(root==null)
        return 0;
    if(root.left == null && root.right == null){
        return 1;
    }

    if(root.left==null)
        return minDepth(root.right) + 1;
    if(root.right==null)
        return minDepth(root.left) + 1;

    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
}