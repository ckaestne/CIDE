package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
