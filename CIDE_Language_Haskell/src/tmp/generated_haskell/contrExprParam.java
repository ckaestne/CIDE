package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class contrExprParam extends GenASTNode {
  public contrExprParam(ArrayList<contrExprAssign> contrExprAssign, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<contrExprAssign>("contrExprAssign", contrExprAssign)
    }, firstToken, lastToken);
  }
  public contrExprParam(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new contrExprParam(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<contrExprAssign> getContrExprAssign() {
    return ((PropertyList<contrExprAssign>)getProperty("contrExprAssign")).getValue();
  }
}
