package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MoreKindNode extends REKind {
  public MoreKindNode(ASTStringNode _more, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_more", _more)
    }, firstToken, lastToken);
  }
  public MoreKindNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MoreKindNode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_more() {
    return ((PropertyOne<ASTStringNode>)getProperty("_more")).getValue();
  }
}
