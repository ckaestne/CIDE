package tmp.generated_phaskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class fixdecl extends GenASTNode {
  public fixdecl(fixity fixity, ASTStringNode integer, ops ops, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fixity>("fixity", fixity),
      new PropertyZeroOrOne<ASTStringNode>("integer", integer),
      new PropertyOne<ops>("ops", ops)
    }, firstToken, lastToken);
  }
  public fixdecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixdecl(cloneProperties(),firstToken,lastToken);
  }
  public fixity getFixity() {
    return ((PropertyOne<fixity>)getProperty("fixity")).getValue();
  }
  public ASTStringNode getInteger() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("integer")).getValue();
  }
  public ops getOps() {
    return ((PropertyOne<ops>)getProperty("ops")).getValue();
  }
}
