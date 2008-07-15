package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SkipKindNode extends REKind {
  public SkipKindNode(ASTStringNode _skip, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_skip", _skip)
    }, firstToken, lastToken);
  }
  public SkipKindNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SkipKindNode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_skip() {
    return ((PropertyOne<ASTStringNode>)getProperty("_skip")).getValue();
  }
}
