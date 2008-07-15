package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Declarator extends GenASTNode {
  public Declarator(Pointer pointer, DirectDeclarator directDeclarator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Pointer>("pointer", pointer),
      new PropertyOne<DirectDeclarator>("directDeclarator", directDeclarator)
    }, firstToken, lastToken);
  }
  public Declarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Declarator(cloneProperties(),firstToken,lastToken);
  }
  public Pointer getPointer() {
    return ((PropertyZeroOrOne<Pointer>)getProperty("pointer")).getValue();
  }
  public DirectDeclarator getDirectDeclarator() {
    return ((PropertyOne<DirectDeclarator>)getProperty("directDeclarator")).getValue();
  }
}
