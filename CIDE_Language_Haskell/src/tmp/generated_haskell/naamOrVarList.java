package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class naamOrVarList extends GenASTNode {
  public naamOrVarList(ArrayList<naamOrVar> naamOrVar, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<naamOrVar>("naamOrVar", naamOrVar)
    }, firstToken, lastToken);
  }
  public naamOrVarList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new naamOrVarList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<naamOrVar> getNaamOrVar() {
    return ((PropertyList<naamOrVar>)getProperty("naamOrVar")).getValue();
  }
}
