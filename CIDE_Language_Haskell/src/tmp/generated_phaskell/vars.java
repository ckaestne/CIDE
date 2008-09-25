package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

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
