package tmp.generated_haskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class patrMain2 extends patrMain {
  public patrMain2(var var, ASTStringNode integer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var),
      new PropertyOne<ASTStringNode>("integer", integer)
    }, firstToken, lastToken);
  }
  public patrMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patrMain2(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
  public ASTStringNode getInteger() {
    return ((PropertyOne<ASTStringNode>)getProperty("integer")).getValue();
  }
}
