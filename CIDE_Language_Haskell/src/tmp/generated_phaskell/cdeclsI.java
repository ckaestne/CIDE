package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
