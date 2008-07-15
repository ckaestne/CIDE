package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StructDeclarator2 extends StructDeclarator {
  public StructDeclarator2(Declarator declarator1, ConstantExpression constantExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Declarator>("declarator1", declarator1),
      new PropertyOne<ConstantExpression>("constantExpression", constantExpression)
    }, firstToken, lastToken);
  }
  public StructDeclarator2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StructDeclarator2(cloneProperties(),firstToken,lastToken);
  }
  public Declarator getDeclarator1() {
    return ((PropertyZeroOrOne<Declarator>)getProperty("declarator1")).getValue();
  }
  public ConstantExpression getConstantExpression() {
    return ((PropertyOne<ConstantExpression>)getProperty("constantExpression")).getValue();
  }
}
