package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StringNode extends Terminal {
  public StringNode(ASTStringNode string, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("string", string)
    }, firstToken, lastToken);
  }
  public StringNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StringNode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getString() {
    return ((PropertyOne<ASTStringNode>)getProperty("string")).getValue();
  }
}
