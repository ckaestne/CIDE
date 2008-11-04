package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class contrPatrParam extends GenASTNode {
  public contrPatrParam(ArrayList<contrPatrAssign> contrPatrAssign, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<contrPatrAssign>("contrPatrAssign", contrPatrAssign)
    }, firstToken, lastToken);
  }
  public contrPatrParam(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new contrPatrParam(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<contrPatrAssign> getContrPatrAssign() {
    return ((PropertyList<contrPatrAssign>)getProperty("contrPatrAssign")).getValue();
  }
}
