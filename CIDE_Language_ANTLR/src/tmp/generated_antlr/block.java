package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class block extends GenASTNode {
  public block(altList altList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<altList>("altList", altList)
    }, firstToken, lastToken);
  }
  public block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new block(cloneProperties(),firstToken,lastToken);
  }
  public altList getAltList() {
    return ((PropertyOne<altList>)getProperty("altList")).getValue();
  }
}
