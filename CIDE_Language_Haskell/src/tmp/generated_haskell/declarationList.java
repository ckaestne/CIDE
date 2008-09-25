package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class declarationList extends GenASTNode {
  public declarationList(ArrayList<declaration> declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<declaration>("declaration", declaration)
    }, firstToken, lastToken);
  }
  public declarationList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declarationList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<declaration> getDeclaration() {
    return ((PropertyList<declaration>)getProperty("declaration")).getValue();
  }
}
