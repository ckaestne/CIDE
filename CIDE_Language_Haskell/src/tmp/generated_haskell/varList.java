package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varList extends GenASTNode {
  public varList(ArrayList<var> var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<var>("var", var)
    }, firstToken, lastToken);
  }
  public varList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<var> getVar() {
    return ((PropertyList<var>)getProperty("var")).getValue();
  }
}
