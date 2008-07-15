package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DeclarationSpecifiers3 extends DeclarationSpecifiers {
  public DeclarationSpecifiers3(TypeQualifier typeQualifier, DeclarationSpecifiers declarationSpecifiers2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeQualifier>("typeQualifier", typeQualifier),
      new PropertyZeroOrOne<DeclarationSpecifiers>("declarationSpecifiers2", declarationSpecifiers2)
    }, firstToken, lastToken);
  }
  public DeclarationSpecifiers3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DeclarationSpecifiers3(cloneProperties(),firstToken,lastToken);
  }
  public TypeQualifier getTypeQualifier() {
    return ((PropertyOne<TypeQualifier>)getProperty("typeQualifier")).getValue();
  }
  public DeclarationSpecifiers getDeclarationSpecifiers2() {
    return ((PropertyZeroOrOne<DeclarationSpecifiers>)getProperty("declarationSpecifiers2")).getValue();
  }
}
