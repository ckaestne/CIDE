package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class exportList extends GenASTNode {
  public exportList(ArrayList<export> export, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<export>("export", export)
    }, firstToken, lastToken);
  }
  public exportList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exportList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<export> getExport() {
    return ((PropertyList<export>)getProperty("export")).getValue();
  }
}
