package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class cnamelist extends GenASTNode {
  public cnamelist(ArrayList<cname> cname, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<cname>("cname", cname)
    }, firstToken, lastToken);
  }
  public cnamelist(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cnamelist(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<cname> getCname() {
    return ((PropertyList<cname>)getProperty("cname")).getValue();
  }
}
