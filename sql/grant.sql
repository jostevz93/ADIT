GRANT select, insert, update ON &&ADIT_SCHEMA..ACCESS_RESTRICTION TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..ADIT_LOG TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..ADIT_USER TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_DVK_STATUS TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_FILE TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_HISTORY TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_HISTORY_TYPE TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_SHARING TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_SHARING_TYPE TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_TYPE TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOCUMENT_WF_STATUS TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..DOWNLOAD_REQUEST_LOG TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..ERROR_LOG TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..METADATA_REQUEST_LOG TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..NOTIFICATION TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..NOTIFICATION_TYPE TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..REMOTE_APPLICATION TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..REQUEST_LOG TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..SIGNATURE TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..USER_NOTIFICATION TO &&ADIT_APP.;
GRANT select, insert, update ON &&ADIT_SCHEMA..USERTYPE TO &&ADIT_APP.;
GRANT execute ON &&ADIT_SCHEMA..deflate_file TO &&ADIT_APP.;

-- Create synonyms for ADIT_APP
CREATE OR REPLACE SYNONYM &&ADIT_APP..ADIT_USER FOR &&ADIT_SCHEMA..ADIT_USER;
CREATE OR REPLACE SYNONYM &&ADIT_APP..ACCESS_RESTRICTION FOR &&ADIT_SCHEMA..ACCESS_RESTRICTION;
CREATE OR REPLACE SYNONYM &&ADIT_APP..ADIT_LOG FOR &&ADIT_SCHEMA..ADIT_LOG;
CREATE OR REPLACE SYNONYM &&ADIT_APP..ADIT_USER FOR &&ADIT_SCHEMA..ADIT_USER;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT FOR &&ADIT_SCHEMA..DOCUMENT;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_DVK_STATUS FOR &&ADIT_SCHEMA..DOCUMENT_DVK_STATUS;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_FILE FOR &&ADIT_SCHEMA..DOCUMENT_FILE;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_HISTORY FOR &&ADIT_SCHEMA..DOCUMENT_HISTORY;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_HISTORY_TYPE FOR &&ADIT_SCHEMA..DOCUMENT_HISTORY_TYPE;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_SHARING FOR &&ADIT_SCHEMA..DOCUMENT_SHARING;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_SHARING_TYPE FOR &&ADIT_SCHEMA..DOCUMENT_SHARING_TYPE;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_TYPE FOR &&ADIT_SCHEMA..DOCUMENT_TYPE;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOCUMENT_WF_STATUS FOR &&ADIT_SCHEMA..DOCUMENT_WF_STATUS;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DOWNLOAD_REQUEST_LOG FOR &&ADIT_SCHEMA..DOWNLOAD_REQUEST_LOG;
CREATE OR REPLACE SYNONYM &&ADIT_APP..ERROR_LOG FOR &&ADIT_SCHEMA..ERROR_LOG;
CREATE OR REPLACE SYNONYM &&ADIT_APP..METADATA_REQUEST_LOG FOR &&ADIT_SCHEMA..METADATA_REQUEST_LOG;
CREATE OR REPLACE SYNONYM &&ADIT_APP..NOTIFICATION FOR &&ADIT_SCHEMA..NOTIFICATION;
CREATE OR REPLACE SYNONYM &&ADIT_APP..NOTIFICATION_TYPE FOR &&ADIT_SCHEMA..NOTIFICATION_TYPE;
CREATE OR REPLACE SYNONYM &&ADIT_APP..REMOTE_APPLICATION FOR &&ADIT_SCHEMA..REMOTE_APPLICATION;
CREATE OR REPLACE SYNONYM &&ADIT_APP..REQUEST_LOG FOR &&ADIT_SCHEMA..REQUEST_LOG;
CREATE OR REPLACE SYNONYM &&ADIT_APP..SIGNATURE FOR &&ADIT_SCHEMA..SIGNATURE;
CREATE OR REPLACE SYNONYM &&ADIT_APP..USER_NOTIFICATION FOR &&ADIT_SCHEMA..USER_NOTIFICATION;
CREATE OR REPLACE SYNONYM &&ADIT_APP..USERTYPE FOR &&ADIT_SCHEMA..USERTYPE;
CREATE OR REPLACE SYNONYM &&ADIT_APP..DEFLATE_FILE FOR &&ADIT_SCHEMA..DEFLATE_FILE;
