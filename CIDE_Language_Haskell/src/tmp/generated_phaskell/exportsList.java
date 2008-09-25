package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class exportsList extends GenASTNode {
  public exportsList(ArrayList<export> export, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<export>("export", export)
    }, firstToken, lastToken);
  }
  public exportsList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exportsList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<export> getExport() {
    return ((PropertyList<export>)getProperty("export")).getValue();
  }
}
