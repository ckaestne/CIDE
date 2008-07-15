package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CompilationUnit extends GenASTNode {
  public CompilationUnit(PackageDeclaration packageDeclaration, ArrayList<ImportDeclaration> importDeclaration, ArrayList<TypeDeclaration> typeDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<PackageDeclaration>("packageDeclaration", packageDeclaration),
      new PropertyZeroOrMore<ImportDeclaration>("importDeclaration", importDeclaration),
      new PropertyZeroOrMore<TypeDeclaration>("typeDeclaration", typeDeclaration)
    }, firstToken, lastToken);
  }
  public CompilationUnit(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CompilationUnit(cloneProperties(),firstToken,lastToken);
  }
  public PackageDeclaration getPackageDeclaration() {
    return ((PropertyZeroOrOne<PackageDeclaration>)getProperty("packageDeclaration")).getValue();
  }
  public ArrayList<ImportDeclaration> getImportDeclaration() {
    return ((PropertyZeroOrMore<ImportDeclaration>)getProperty("importDeclaration")).getValue();
  }
  public ArrayList<TypeDeclaration> getTypeDeclaration() {
    return ((PropertyZeroOrMore<TypeDeclaration>)getProperty("typeDeclaration")).getValue();
  }
}
