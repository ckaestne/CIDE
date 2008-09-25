package tmp.generated_haskell;

import cide.gast.ASTNode;
import cide.gast.IToken;
import cide.gast.NoToken;
import cide.gast.Property;
import cide.gparser.Token;
import cide.gparser.WToken;

public abstract class GenASTNode extends ASTNode {
  public GenASTNode(Property[] p, Token firstToken, Token lastToken) {
    		super(p, 
    		    		(lastToken.next == firstToken ? new NoToken(firstToken.offset) : new WToken(firstToken)), 
    		    		(lastToken.next == firstToken ? new NoToken(firstToken.offset)	: new WToken(lastToken)));
  }
  public GenASTNode(Property[] p, IToken firstToken, IToken lastToken) {
    super(p, firstToken, lastToken);
  }
  public String toString() {
    return this.getClass().getSimpleName() + " " + this.getStartPosition()
        + "-" + (this.getStartPosition() + this.getLength());
  }
  public String render() {
    SimplePrintVisitor v=new SimplePrintVisitor();
    accept(v);
    return v.getResult();
  }
}
