package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SpecifierQualifierList2 extends SpecifierQualifierList {
  public SpecifierQualifierList2(TypeQualifier typeQualifier, SpecifierQualifierList specifierQualifierList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeQualifier>("typeQualifier", typeQualifier),
      new PropertyZeroOrOne<SpecifierQualifierList>("specifierQualifierList1", specifierQualifierList1)
    }, firstToken, lastToken);
  }
  public SpecifierQualifierList2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SpecifierQualifierList2(cloneProperties(),firstToken,lastToken);
  }
  public TypeQualifier getTypeQualifier() {
    return ((PropertyOne<TypeQualifier>)getProperty("typeQualifier")).getValue();
  }
  public SpecifierQualifierList getSpecifierQualifierList1() {
    return ((PropertyZeroOrOne<SpecifierQualifierList>)getProperty("specifierQualifierList1")).getValue();
  }
}
