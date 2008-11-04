package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class funlhsR2 extends funlhsR {
  public funlhsR2(ASTStringNode finduntilsemiorequals, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("finduntilsemiorequals", finduntilsemiorequals)
    }, firstToken, lastToken);
  }
  public funlhsR2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new funlhsR2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFinduntilsemiorequals() {
    return ((PropertyOne<ASTStringNode>)getProperty("finduntilsemiorequals")).getValue();
  }
}
