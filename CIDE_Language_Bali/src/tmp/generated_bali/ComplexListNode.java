package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ComplexListNode extends PrimitiveRewrite {
  public ComplexListNode(Lookahead lookahead, Primitive primitive, Primitive primitive1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Lookahead>("lookahead", lookahead),
      new PropertyOne<Primitive>("primitive", primitive),
      new PropertyOne<Primitive>("primitive1", primitive1)
    }, firstToken, lastToken);
  }
  public ComplexListNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ComplexListNode(cloneProperties(),firstToken,lastToken);
  }
  public Lookahead getLookahead() {
    return ((PropertyZeroOrOne<Lookahead>)getProperty("lookahead")).getValue();
  }
  public Primitive getPrimitive() {
    return ((PropertyOne<Primitive>)getProperty("primitive")).getValue();
  }
  public Primitive getPrimitive1() {
    return ((PropertyOne<Primitive>)getProperty("primitive1")).getValue();
  }
}
