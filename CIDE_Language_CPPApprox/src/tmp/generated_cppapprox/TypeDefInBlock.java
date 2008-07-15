package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDefInBlock extends CodeUnit_InBlock {
  public TypeDefInBlock(TypeDef typeDef, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeDef>("typeDef", typeDef)
    }, firstToken, lastToken);
  }
  public TypeDefInBlock(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDefInBlock(cloneProperties(),firstToken,lastToken);
  }
  public TypeDef getTypeDef() {
    return ((PropertyOne<TypeDef>)getProperty("typeDef")).getValue();
  }
}
