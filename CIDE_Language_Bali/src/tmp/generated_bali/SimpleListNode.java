package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SimpleListNode extends Rewrite {
  public SimpleListNode(Lookahead lookahead, Primitive primitive, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Lookahead>("lookahead", lookahead),
      new PropertyOne<Primitive>("primitive", primitive)
    }, firstToken, lastToken);
  }
  public SimpleListNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SimpleListNode(cloneProperties(),firstToken,lastToken);
  }
  public Lookahead getLookahead() {
    return ((PropertyZeroOrOne<Lookahead>)getProperty("lookahead")).getValue();
  }
  public Primitive getPrimitive() {
    return ((PropertyOne<Primitive>)getProperty("primitive")).getValue();
  }
}
