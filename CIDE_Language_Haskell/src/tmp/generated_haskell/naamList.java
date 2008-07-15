package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class naamList extends GenASTNode {
  public naamList(ArrayList<naam> naam, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<naam>("naam", naam)
    }, firstToken, lastToken);
  }
  public naamList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new naamList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<naam> getNaam() {
    return ((PropertyList<naam>)getProperty("naam")).getValue();
  }
}
