package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AbstractDeclarator2 extends AbstractDeclarator {
  public AbstractDeclarator2(Pointer pointer1, DirectAbstractDeclarator directAbstractDeclarator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Pointer>("pointer1", pointer1),
      new PropertyOne<DirectAbstractDeclarator>("directAbstractDeclarator", directAbstractDeclarator)
    }, firstToken, lastToken);
  }
  public AbstractDeclarator2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AbstractDeclarator2(cloneProperties(),firstToken,lastToken);
  }
  public Pointer getPointer1() {
    return ((PropertyZeroOrOne<Pointer>)getProperty("pointer1")).getValue();
  }
  public DirectAbstractDeclarator getDirectAbstractDeclarator() {
    return ((PropertyOne<DirectAbstractDeclarator>)getProperty("directAbstractDeclarator")).getValue();
  }
}
