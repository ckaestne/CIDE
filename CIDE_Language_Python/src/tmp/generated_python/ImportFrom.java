package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ImportFrom extends GenASTNode {
  public ImportFrom(dotted_name dotted_name, ASTStringNode import_kw, ImportFromEnd importFromEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<dotted_name>("dotted_name", dotted_name),
      new PropertyOne<ASTStringNode>("import_kw", import_kw),
      new PropertyOne<ImportFromEnd>("importFromEnd", importFromEnd)
    }, firstToken, lastToken);
  }
  public ImportFrom(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ImportFrom(cloneProperties(),firstToken,lastToken);
  }
  public dotted_name getDotted_name() {
    return ((PropertyOne<dotted_name>)getProperty("dotted_name")).getValue();
  }
  public ASTStringNode getImport_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("import_kw")).getValue();
  }
  public ImportFromEnd getImportFromEnd() {
    return ((PropertyOne<ImportFromEnd>)getProperty("importFromEnd")).getValue();
  }
}
