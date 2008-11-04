package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class vars extends GenASTNode {
  public vars(ArrayList<var> var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<var>("var", var)
    }, firstToken, lastToken);
  }
  public vars(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new vars(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<var> getVar() {
    return ((PropertyList<var>)getProperty("var")).getValue();
  }
}
