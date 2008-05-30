package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CDSect extends GenASTNode {
  public CDSect(ASTStringNode cdend, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("cdend", cdend)
    }, firstToken, lastToken);
  }
  public CDSect(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new CDSect(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getCdend() {
    return ((PropertyOne<ASTStringNode>)getProperty("cdend")).getValue();
  }
}
