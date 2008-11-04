package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
