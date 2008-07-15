package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
