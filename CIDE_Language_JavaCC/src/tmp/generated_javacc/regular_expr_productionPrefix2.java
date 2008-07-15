package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class regular_expr_productionPrefix2 extends regular_expr_productionPrefix {
  public regular_expr_productionPrefix2(ArrayList<ASTStringNode> identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public regular_expr_productionPrefix2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new regular_expr_productionPrefix2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTStringNode> getIdentifier() {
    return ((PropertyList<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
