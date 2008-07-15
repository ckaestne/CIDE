package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DeclarationSpecifiers2 extends DeclarationSpecifiers {
  public DeclarationSpecifiers2(TypeSpecifier typeSpecifier, DeclarationSpecifiers declarationSpecifiers1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeSpecifier>("typeSpecifier", typeSpecifier),
      new PropertyZeroOrOne<DeclarationSpecifiers>("declarationSpecifiers1", declarationSpecifiers1)
    }, firstToken, lastToken);
  }
  public DeclarationSpecifiers2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DeclarationSpecifiers2(cloneProperties(),firstToken,lastToken);
  }
  public TypeSpecifier getTypeSpecifier() {
    return ((PropertyOne<TypeSpecifier>)getProperty("typeSpecifier")).getValue();
  }
  public DeclarationSpecifiers getDeclarationSpecifiers1() {
    return ((PropertyZeroOrOne<DeclarationSpecifiers>)getProperty("declarationSpecifiers1")).getValue();
  }
}
