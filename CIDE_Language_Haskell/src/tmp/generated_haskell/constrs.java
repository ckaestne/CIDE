package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constrs extends GenASTNode {
  public constrs(ArrayList<constr> constr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<constr>("constr", constr)
    }, firstToken, lastToken);
  }
  public constrs(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constrs(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<constr> getConstr() {
    return ((PropertyList<constr>)getProperty("constr")).getValue();
  }
}
