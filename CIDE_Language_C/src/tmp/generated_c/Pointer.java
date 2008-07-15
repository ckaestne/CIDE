package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Pointer extends GenASTNode {
  public Pointer(TypeQualifierList typeQualifierList, Pointer pointer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<TypeQualifierList>("typeQualifierList", typeQualifierList),
      new PropertyZeroOrOne<Pointer>("pointer", pointer)
    }, firstToken, lastToken);
  }
  public Pointer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Pointer(cloneProperties(),firstToken,lastToken);
  }
  public TypeQualifierList getTypeQualifierList() {
    return ((PropertyZeroOrOne<TypeQualifierList>)getProperty("typeQualifierList")).getValue();
  }
  public Pointer getPointer() {
    return ((PropertyZeroOrOne<Pointer>)getProperty("pointer")).getValue();
  }
}
