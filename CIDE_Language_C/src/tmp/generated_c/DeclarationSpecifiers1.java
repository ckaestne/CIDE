package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DeclarationSpecifiers1 extends DeclarationSpecifiers {
  public DeclarationSpecifiers1(StorageClassSpecifier storageClassSpecifier, DeclarationSpecifiers declarationSpecifiers, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StorageClassSpecifier>("storageClassSpecifier", storageClassSpecifier),
      new PropertyZeroOrOne<DeclarationSpecifiers>("declarationSpecifiers", declarationSpecifiers)
    }, firstToken, lastToken);
  }
  public DeclarationSpecifiers1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DeclarationSpecifiers1(cloneProperties(),firstToken,lastToken);
  }
  public StorageClassSpecifier getStorageClassSpecifier() {
    return ((PropertyOne<StorageClassSpecifier>)getProperty("storageClassSpecifier")).getValue();
  }
  public DeclarationSpecifiers getDeclarationSpecifiers() {
    return ((PropertyZeroOrOne<DeclarationSpecifiers>)getProperty("declarationSpecifiers")).getValue();
  }
}
