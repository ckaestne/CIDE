package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class topdecls extends GenASTNode {
  public topdecls(ArrayList<topdecl> topdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<topdecl>("topdecl", topdecl)
    }, firstToken, lastToken);
  }
  public topdecls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new topdecls(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<topdecl> getTopdecl() {
    return ((PropertyList<topdecl>)getProperty("topdecl")).getValue();
  }
}
