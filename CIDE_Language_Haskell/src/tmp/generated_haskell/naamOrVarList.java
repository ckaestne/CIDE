package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

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
