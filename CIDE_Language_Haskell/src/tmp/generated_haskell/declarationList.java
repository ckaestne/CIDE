package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
