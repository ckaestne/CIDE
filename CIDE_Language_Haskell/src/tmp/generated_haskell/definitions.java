package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class definitions extends GenASTNode {
  public definitions(ArrayList<definition> definition, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<definition>("definition", definition)
    }, firstToken, lastToken);
  }
  public definitions(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new definitions(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<definition> getDefinition() {
    return ((PropertyList<definition>)getProperty("definition")).getValue();
  }
}
