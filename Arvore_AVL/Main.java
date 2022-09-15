public class Main {
	public static void main (String args[]){
		Arvore tree = new Arvore();
		
		//Insercao
		tree.insert(200);
		tree.insert(100);
		tree.insert(150);
		tree.insert(170);
		tree.insert(120);
		System.out.print("Arvore:");
		tree.displayTree();
		
		//Remocao
		tree.remove(150);
		System.out.print("Arvore:");
		tree.displayTree();
	}
}
