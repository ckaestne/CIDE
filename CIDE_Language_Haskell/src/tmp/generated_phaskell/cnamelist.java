package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
