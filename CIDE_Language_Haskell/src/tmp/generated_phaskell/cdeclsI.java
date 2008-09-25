package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class cdeclsI extends GenASTNode {
  public cdeclsI(ArrayList<cdecl> cdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<cdecl>("cdecl", cdecl)
    }, firstToken, lastToken);
  }
  public cdeclsI(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cdeclsI(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<cdecl> getCdecl() {
    return ((PropertyList<cdecl>)getProperty("cdecl")).getValue();
  }
}
